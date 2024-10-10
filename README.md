## Todo

```mermaid
graph LR
;
    A[持久层<br>PostgreSQL<br>]
    B[缓存<br>Redis<br>]
    C[消息队列<br>RabbitMQ<br>]
    D[前端<br>Vue.js<br>]
    E[后端Api层<br>Nutz<br>]
    D --> C --> E
    E --> A
    E --> B
    E --> B --> A
```

#### 1. 后端具体技术栈

- [x] Core: Nutz
- [ ] Logging: Log4j2 + Slf4j
- [ ] Authentication: Sa Token
- [ ] Database: PostgreSQL
- [ ] Cache: Redis
- [ ] Session: Redis
- [ ] File Storage: Minio
- [ ] *Message Queue: RabbitMQ*
- [ ] *特殊功能: OAuth2.0*

#### 2. 前端具体技术栈

- [ ] Core: Vue.js
- [ ] HTTP Request: Axios

---

## 结构

### 后端
