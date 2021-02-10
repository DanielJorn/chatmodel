package daniel.chatmodel.features.chats

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private const val TAG = "ChatListViewModel"

class ChatListViewModel: ViewModel(){
    val chatList = ArrayList<ChatPreviewModel>()
    /*private val mutableChatList = MutableLiveData<List<ChatPreviewModel>>()
    val chatListLiveData: LiveData<List<ChatPreviewModel>> = mutableChatList*/
    private val mutableUpdateLiveData = MutableLiveData<Unit>()
    val updateLiveData : LiveData<Unit> = mutableUpdateLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> = errorLiveData

    private val database = Firebase.database
    private val chatsRef = database.getReference("chats")

    fun updateChatList(){
        chatsRef.addChildEventListener(object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatPreview = snapshot.getValue(ChatPreviewModel::class.java)!!
                Log.d(TAG, "onChildAdded: $chatPreview")

                chatList.add(chatPreview)
                mutableUpdateLiveData.value = mutableUpdateLiveData.value
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val changedChatModel = snapshot.getValue(ChatPreviewModel::class.java)!!
                Log.d(TAG, "onChildChanged: chat entity: $changedChatModel")

                chatList.forEachIndexed { currInd, currChatModel ->
                    val nullPos = -1
                    var indToChange = nullPos

                    if(currChatModel.id == changedChatModel.id)
                        indToChange = currInd
                    if(indToChange != nullPos)
                        chatList[indToChange] = changedChatModel
                }
                mutableUpdateLiveData.value = mutableUpdateLiveData.value
            }

            override fun onCancelled(error: DatabaseError) {
                errorLiveData.value = error.message
                Log.d(TAG, "onCancelled: error msg is: ${error.message}")
                Log.d(TAG, "onCancelled: error details are: ${error.details}")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d(TAG, "onChildMoved: snapshot")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.d(TAG, "onChildRemoved: ")
            }

        })

    }
}