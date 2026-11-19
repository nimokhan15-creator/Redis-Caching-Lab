
package com.example.rediscaching;

public class ProductService {

    private final DatabaseService databaseService;
    private final RedisService redisService;

    private int cacheHits = 0;
    private int cacheMisses = 0;
    public void updatePrice(int id, java.math.BigDecimal price) {

        databaseService.updatePrice(id, price);

        try (redis.clients.jedis.Jedis jedis =
                     new redis.clients.jedis.Jedis("localhost", 6380)) {

            jedis.del("product:" + id);
        }
    }
    public ProductService() {
        this.databaseService = new DatabaseService();
        this.redisService = new RedisService();
    }

    public Product getProduct(int id) {

        Product product = redisService.getProduct(id);

        if (product != null) {
            cacheHits++;
            return product;
        }

        cacheMisses++;

        product = databaseService.getProduct(id);

        if (product != null) {
            try {
                redisService.saveProduct(product);
            } catch (Exception e) {
                System.out.println("Could not save to Redis. Continuing with PostgreSQL.");
            }


        return product;
    }

        cacheMisses++;

        product = databaseService.getProduct(id);

        if (product != null) {
            redisService.saveProduct(product);
        }

        return product;
    }

    public int getCacheHits() {
        return cacheHits;
    }

    public int getCacheMisses() {
        return cacheMisses;
    }
}
