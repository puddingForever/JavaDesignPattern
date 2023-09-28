package intersegrega.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import intersegrega.entity.Order;

public class OrderPersistanceService implements PersistenceService<Order>{

	private static final Map<Long, Order> ORDERS = new HashMap<>();
	
	@Override
	public void save(Order entity) {
		synchronized(ORDERS) {
			ORDERS.put(entity.getId(), entity);
		}
	}
	
	@Override
	public void delete(Order entity) {
		synchronized(ORDERS) {
			ORDERS.remove(entity.getId());
		}
	}

	@Override
	public Order findById(Long id) {
		synchronized(ORDERS) {
			return ORDERS.get(id);
		}
	}

	//break interface segregation principle
//	@Override
//	public List<Order> findByName(String name) {
//		synchronized(ORDERS) {
//			throw new UnsupportedOperationException("Find by name is not supported");
//		}
//	}

	
	
}
