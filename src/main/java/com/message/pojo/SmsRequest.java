package com.message.pojo;

import java.util.List;

public class SmsRequest {

	private Account Account;
	private List<Msg> Messages;

	public Account getAccount() {
		return Account;
	}

	public void setAccount(Account account) {
		Account = account;
	}

	public List<Msg> getMessages() {
		return Messages;
	}

	public void setMessages(List<Msg> messages) {
		Messages = messages;
	}

}
