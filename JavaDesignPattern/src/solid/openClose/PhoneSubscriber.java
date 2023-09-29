package solid.openClose;

import java.util.List;

//핸드폰 사용자 계정 클라스 
public class PhoneSubscriber extends Subscriber{


	//open for modification 
	@Override
    public double calculateBill() {
        List<CallHistory.Call> sessions = CallHistory.getCurrentCalls(subscriberId);
        long totalDuration = sessions.stream().mapToLong(CallHistory.Call::getDuration).sum();
        return totalDuration*baseRate/100;
    }

    
}