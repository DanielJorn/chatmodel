package daniel.chatmodel.base.firestore

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

fun Query.handleUpdates(onSuccess: (QuerySnapshot) -> Unit, onFailure: (FirebaseFirestoreException) -> Unit): ListenerRegistration {
    return addSnapshotListener { snapshot, error ->
        if (error != null) onFailure(error)
        else onSuccess(snapshot!!)
    }
}