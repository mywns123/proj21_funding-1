package proj21_funding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj21_funding.dto.FundingInfo;
import proj21_funding.dto.Message;
import proj21_funding.dto.Project;
import proj21_funding.dto.account.UserInfo;
import proj21_funding.exception.UserNotFoundException;
import proj21_funding.mapper.FundingInfoMapper;
import proj21_funding.mapper.MessageMapper;
import proj21_funding.mapper.ProjectMapper;
import proj21_funding.mapper.UserInfoMapper;
import proj21_funding.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper mapper;

	@Autowired
	private UserInfoMapper userMapper;
	
	@Autowired
	private ProjectMapper proMapper;

	@Autowired
	private FundingInfoMapper FundingMapper;
	
	@Override
	public int sendMessage(Message message) {		
		String receive = message.getReceiveUser();
		String[] user = receive.split(", ");

		for(int i = 0; i < user.length; i++) {			
			message.setReceiveUser(user[i]);			
			
			UserInfo info =	userMapper.selectUserbyId(message.getReceiveUser());
			if(info == null) {				
				throw new UserNotFoundException();
			}	
			Message newMessage = new Message(message.getSendUser(), message.getReceiveUser(), message.getMsgContent());
			mapper.insertMessage(newMessage);			
		}
		return 0;		
	}

	@Override
	public List<Message> showBySendUser(String sendUser) {		
		return mapper.selectBySendUser(sendUser);
	}

	@Override
	public List<Message> showByreceiveUser(String receiveUser) {			
		return mapper.selectByreceiveUser(receiveUser);
	}

	@Override
	public List<Message> showByreceiveUserUnRead(String receiveUser) {
		return mapper.selectByRead(receiveUser);
	}

	@Override
	public Message showByMsgNo(int msgNo) {		
		return mapper.selectByMsgNo(msgNo);
	}

	@Override
	public int readMessage(Message message) {		
		return mapper.updateMessage(message);
	}

	@Override
	public int removeReceiveMessage(Message message) {	
		return mapper.delReceiveMessage(message);
	}

	@Override
	public int removeSendMessage(Message message) {		
		return mapper.delSendMessage(message);
	}

	@Override
	public List<Project> showProjectsByUser(int userNo) {
		return proMapper.selectProjectByUser(userNo);
	}

	@Override
	public List<FundingInfo> showFundingInfosByPrjNo(int prjNo) {		
		return FundingMapper.selectFundingInfoByPrjNo(prjNo);
	}

	@Override
	public UserInfo showUserbyNo(int userNo) {
		return userMapper.selectUserbyNo(userNo);
	}

	
}
