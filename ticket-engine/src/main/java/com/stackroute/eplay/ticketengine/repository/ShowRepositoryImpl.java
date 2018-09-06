package com.stackroute.eplay.ticketengine.repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.ticketengine.domain.Show;

@Repository
public class ShowRepositoryImpl implements ShowRepository {

	private static final String KEY = "Show";

	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, Long, Show> hashOperations;
	
	@Autowired
	public ShowRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(Show show) {
		hashOperations.put(KEY, show.getShowId(), show);
	}

	@Override
	public Show find(Long id) {
		return hashOperations.get(KEY, id);
	}

	@Override
	public Map<Long, Show> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public void update(Show show) {
		hashOperations.put(KEY, show.getShowId(), show);
	}

	@Override
	public void delete(Long id) {
		hashOperations.delete(KEY, id);
	}

}
