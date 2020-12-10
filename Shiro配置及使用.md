### 12月 8 日报

- 使用 Hutools 工具生成随机数 等工具操作
- 发送短信验证码



## Redis

什么是redis 

> 非关系键值对 内存数据
>
> 键：是字符串类型
>
> 值：字符串 集合 有序集合 hash 列表

优点：

- 丰富的数据类型
- 性能强大
- 支持持久化
- 支持事务

应用场景

- 缓存
- session共享
- 分布式锁
- 幂等性
- 签到

#### 程序中使用java 连接数据库客户端

- jedis
- redis 官方推荐 api 比较丰富
- lettuce  springboot 推荐 分布式缓存
- redisson  分布式锁（简易）



```java

 @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory, RedisSerializer<?> redisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //直接key的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //  直接的序列化
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
//        讲自定有的redisTemplate注册到工厂类
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

```

```java

@Bean
@Primary
public Jackson2JsonRedisSerializer<Object> redisSerializer() {
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    jackson2JsonRedisSerializer.setObjectMapper(om);
    return jackson2JsonRedisSerializer;
}

```

配置文件

```yml

server:
  port: 6800
spring:
  redis:
    # 16 个数据  0-15
    database: 1
    host: 114.215.196.38
    port: 6379
    password: ""
    jedis:
      pool:
        max-active: 8
        max-idle: 6
        max-wait: -1
        min-idle: 0

```



#### 分布式缓存雪崩

- 缓存雪崩

  > 缓存雪崩值得是在统一时间内，又大量的缓存数据失效，在这期间，原本应该访问缓存的请求，转而去访问数据库，这会对数据库CPU内存照成巨大的压力，验证的还会造成数据库死机，从而造成一系列的连锁反应，照成系统崩溃。

  应对缓存雪崩一般有3中处理方式

  1、在并发量步数很大的时候，加入分布式锁

  2、给缓存添加标记，如果缓存失效的话，从数据库中获取并更新到缓存中

- 缓存穿透

  > 缓存穿透值得是请求访问一个不存在的数据，数据库中不存在，缓冲中也不存在，请求先访问缓存，再去访问数据库，最后返回的结果是空的，相当于执行了两次无用操作。

- 缓存击穿

  > 缓存击穿值是数据库中还有，在缓存中没有，一般是缓存时间过期，先去缓冲中访问，再去数据库中访问，会造成数据库的压力瞬间增大，造成数据库崩溃

- 缓存预热

  > 缓存预热值得是系统上线后，将相关的数据库先加载到缓冲中，当有请求访问时，会先从数据库中查找到的数据，加载到缓存中，缓冲预热避免了请求直接访问数据库。

- 缓存更新

  > 缓存跟新就是缓存服务器自带的缓存失效策略，我们可以根据具体的情况添加对应的策略