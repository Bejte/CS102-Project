package com.example.catastrophecompass.UILayer.Common.Chat;

import com.example.catastrophecompass.DataLayer.Model.RecentChatItem;

public interface ChatFragmentInterface {
    void setDisplay(RecentChatItem recentChatItem);
    void warnUser();
}
