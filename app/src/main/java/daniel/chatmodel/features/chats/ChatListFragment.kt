package daniel.chatmodel.features.chats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import daniel.chatmodel.R
import kotlinx.android.synthetic.main.fragment_chat_list.*

class ChatListFragment: Fragment(R.layout.fragment_chat_list){
    private lateinit var adapter: ChatPreviewAdapter
    private val viewModel: ChatListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* val chat1 = ChatPreviewModel("Chat1", "Some message")
        val chat2 = ChatPreviewModel("Chat12", "asdkap message")
        val chat3 = ChatPreviewModel("Chat13", "He needs some milk")*/

        viewModel.updateLiveData.observe(viewLifecycleOwner) { onChatListUpdated() }


        adapter = ChatPreviewAdapter(viewModel.chatList)
        viewModel.updateChatList()
        rv_chat_list.adapter=adapter

        /*

        fakeList.add(chat1)
        fakeList.add(chat2)
        fakeList.add(chat3)

        adapter.notifyDataSetChanged()*/

    }

    private fun onChatListUpdated() {
        adapter.notifyDataSetChanged()
    }
}