package org.fkjava.weixin.service;

import org.fkjava.weixin.domain.InMessage;
import org.fkjava.weixin.domain.OUtMessage;

public interface MessageService {

	OUtMessage onMessage(InMessage msg);
}
