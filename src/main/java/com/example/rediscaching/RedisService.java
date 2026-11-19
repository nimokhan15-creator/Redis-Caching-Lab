package com.example.rediscaching;

import redis.clients.jedis.Jedis;

public class RedisService {

    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6380;

    public void saveProduct(Product product) {

        try (Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT)) {

            String key = "product:" + product.getId();

            String value = product.getId() + "|" +
                    product.getName() + "|" +
                    product.getPrice();

            jedis.setex(key, 300, value);

        }
    }

    public Product getProduct(int id) {

        try (Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT)) {

            String key = "product:" + id;
            String value = jedis.get(key);

            if (value == null) {
                return null;
            }

            String[] parts = value.split("\\|");

            return new Product(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    new java.math.BigDecimal(parts[2])
            );

        } catch (Exception e) {
            System.out.println("Redis unavailable. Using PostgreSQL.");
            return null;
        }
    }
        }


