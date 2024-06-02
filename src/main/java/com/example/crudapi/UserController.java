package com.example.crudapi;

import com.google.api.core.ApiFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    private final Firestore db = FirestoreClient.getFirestore();
    // Using @PostMapping ,@GetMapping ,@PutMapping @DeleteMapping To perform basic CRUD operations i.e Create ,retrieve, update and Delete

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
//    	 Map<String, Integer> subjectMarks = new HashMap<>();
//         subjectMarks.put("Math", 10); // Default value
//         subjectMarks.put("Science", 0); // Default value
//         // Set subjectMarks to user object
//         user.setSubjectmarks(subjectMarks); To check if any errors are at the time of the operations or data being sent as null etc
    	logger.info("Received User: {}", user);
        db.collection("users").document(user.getId()).set(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            User user = document.toObject(User.class);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        db.collection("users").document(id).set(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        db.collection("users").document(id).delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
