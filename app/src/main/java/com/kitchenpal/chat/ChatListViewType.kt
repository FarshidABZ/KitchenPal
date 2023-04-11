package com.kitchenpal.chat

sealed class ChatListViewType {
    class IncomingTextMessage(val message: String) : ChatListViewType()
    class IncomingImage(val imageUrl: String? = null, val drawableId: Int? = null) :
        ChatListViewType()

    class IncomingInteractionMessage(val message: String) : ChatListViewType()
    class OutgoingTextMessage(val message: String) : ChatListViewType()
    class OutgoingInteractionMessage(val message: String) : ChatListViewType()
}