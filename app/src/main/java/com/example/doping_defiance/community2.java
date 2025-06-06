package com.example.doping_defiance;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.HashMap;
import java.util.Map;

public class community2 extends AppCompatActivity {

    private TextView roomNameValue;
    private EditText messageInput;
    private Button sendMessageButton, leaveRoomButton;
    private DatabaseReference databaseReference;

    private String roomName;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community2);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get room name and username passed from MainActivity2
        roomName = getIntent().getStringExtra("ROOM_NAME");
        username = getIntent().getStringExtra("username");

        if (roomName == null || username == null) {
            Log.e("MainActivity", "Room name or username is null.");
            Toast.makeText(community2.this, "Failed to join chat. Please try again.", Toast.LENGTH_SHORT).show();
            finish();  // Close the activity if data is missing
            return;
        }
        roomNameValue = findViewById(R.id.roomNameValue);
        messageInput = findViewById(R.id.messageInput);
        sendMessageButton = findViewById(R.id.sendMessageButton);
        leaveRoomButton = findViewById(R.id.leaveRoomButton);
        roomNameValue.setText(roomName);

        // Handle Send button click
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        // Handle Leave button click
        leaveRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveRoom();
            }
        });

        // Add the user to the room (for tracking online users)
        addUserToRoom();

        // Listen for incoming messages
        listenForMessages();
    }

    // Method to send a message to the chatroom
    private void sendMessage() {
        String message = messageInput.getText().toString().trim();

        if (TextUtils.isEmpty(message)) {
            Toast.makeText(community2.this, "Please enter a message", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a message object with the username and the message
        long timestamp = System.currentTimeMillis();
        Map<String, Object> messageData = new HashMap<>();
        messageData.put("username", username);
        messageData.put("message", message);
        messageData.put("timestamp", timestamp);

        // Push the message to the Firebase database under the selected room
        databaseReference.child("chatrooms").child(roomName).child("messages").push().setValue(messageData)
                .addOnSuccessListener(aVoid -> {
                    messageInput.setText(""); // Clear the input field after sending
                    Toast.makeText(community2.this, "Message sent", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(community2.this, "Failed to send message", Toast.LENGTH_SHORT).show();
                    Log.e("MainActivity", "Failed to send message: " + e.getMessage());
                });
    }

    // Method to add user to the room
    private void addUserToRoom() {
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);

        // Add the user to the room under the "users" node in Firebase
        databaseReference.child("chatrooms").child(roomName).child("users").child(username).setValue(user)
                .addOnSuccessListener(aVoid -> {
                    Log.d("MainActivity", "User added to room: " + roomName);
                    Toast.makeText(community2.this, "Joined room: " + roomName, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(community2.this, "Failed to join room", Toast.LENGTH_SHORT).show();
                    Log.e("MainActivity", "Failed to join room: " + e.getMessage());
                });
    }

    // Method to leave the room
    private void leaveRoom() {
        // Remove the user from the room under the "users" node in Firebase
        databaseReference.child("chatrooms").child(roomName).child("users").child(username).removeValue()
                .addOnSuccessListener(aVoid -> {
                    // Now clear the messages in the room
                    clearMessages();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(community2.this, "Failed to leave the room", Toast.LENGTH_SHORT).show();
                    Log.e("MainActivity", "Failed to leave room: " + e.getMessage());
                });
    }

    // Method to clear messages when the user leaves the room
    private void clearMessages() {
        // Remove all messages from the current room in Firebase
        Log.d("MainActivity", "Attempting to clear messages for room: " + roomName);

        databaseReference.child("chatrooms").child(roomName).child("messages").removeValue()
                .addOnSuccessListener(aVoid -> {
                    Log.d("MainActivity", "Messages cleared successfully for room: " + roomName);
                    Toast.makeText(community2.this, "Messages cleared", Toast.LENGTH_SHORT).show();
                    // Redirect the user to MainActivity2 (the previous activity)
                    Intent intent = new Intent(community2.this, MainActivity2.class);
                    startActivity(intent);
                    finish(); // Close the current activity
                })
                .addOnFailureListener(e -> {
                    Log.e("MainActivity", "Failed to clear messages for room: " + roomName + " Error: " + e.getMessage());
                    Toast.makeText(community2.this, "Failed to clear messages", Toast.LENGTH_SHORT).show();
                });
    }

    // Method to listen for incoming messages
    private void listenForMessages() {
        // Listen for new messages in the chatroom
        databaseReference.child("chatrooms").child(roomName).child("messages")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Iterate through the messages and update the UI
                        StringBuilder chatMessages = new StringBuilder();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String message = snapshot.child("message").getValue(String.class);
                            String sender = snapshot.child("username").getValue(String.class);
                            long timestamp = snapshot.child("timestamp").getValue(Long.class);

                            // Append message to the chat
                            chatMessages.append(sender).append(": ").append(message).append("\n");
                        }
                        // Display the updated messages
                        displayMessage(chatMessages.toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors here
                        Log.e("MainActivity", "Error listening for messages: " + databaseError.getMessage());
                    }
                });
    }

    // Method to display the message
    private void displayMessage(String messages) {
        TextView chatMessages = findViewById(R.id.chatMessages);
        chatMessages.setText(messages); // Set the concatenated message list
    }
}
