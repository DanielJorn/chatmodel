package daniel.chatmodel.base.firestore

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

fun Query.handleUpdates(onSuccess: (QuerySnapshot?) -> Unit, onFailure: (Exception) -> Unit, allowNullSnapshot: Boolean = false): ListenerRegistration {
    return addSnapshotListener { snapshot, e ->
        val errorOccurred =  e != null
        val snapshotNull = snapshot == null

        when {
            errorOccurred -> onFailure(e!!)
            snapshotNull && !allowNullSnapshot -> onFailure(Exception())
            else -> onSuccess(snapshot)
        }
    }
}