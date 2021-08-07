package daniel.chatmodel.base.firestore

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

fun Query.handleUpdates(onSuccess: (QuerySnapshot) -> Unit, onFailure: (Exception) -> Unit): ListenerRegistration {
    return addSnapshotListener { snapshot, e ->
        when {
            e != null -> onFailure(e)
            snapshot == null -> onFailure(Exception())
            else -> onSuccess(snapshot)
        }
    }
}