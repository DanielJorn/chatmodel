package daniel.chatmodel.base.firestore

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

fun Query.handleUpdates(onSuccess: (QuerySnapshot) -> Unit, onFailure: (FirebaseFirestoreException) -> Unit) {
    addSnapshotListener { snapshot, e ->
        if (e != null) onFailure(e)
        else onSuccess(snapshot!!)
    }
}