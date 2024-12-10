## load test

```bash
hey -n 100000 -c 1000 -m GET http://localhost:8080/customers/1

Summary:
  Total:        25.5792 secs
  Slowest:      0.9783 secs
  Fastest:      0.0007 secs
  Average:      0.2528 secs
  Requests/sec: 3909.4221
  
  Total data:   7800000 bytes
  Size/request: 78 bytes

Response time histogram:
  0.001 [1]     |
  0.098 [1453]  |■
  0.196 [4172]  |■■
  0.294 [84952] |■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
  0.392 [6173]  |■■■
  0.489 [2719]  |■
  0.587 [171]   |
  0.685 [73]    |
  0.783 [98]    |
  0.881 [148]   |
  0.978 [40]    |


Latency distribution:
  10% in 0.2108 secs
  25% in 0.2178 secs
  50% in 0.2318 secs
  75% in 0.2812 secs
  90% in 0.2917 secs
  95% in 0.3660 secs
  99% in 0.4745 secs

Details (average, fastest, slowest):
  DNS+dialup:   0.0003 secs, 0.0007 secs, 0.9783 secs
  DNS-lookup:   0.0002 secs, 0.0000 secs, 0.0950 secs
  req write:    0.0001 secs, 0.0000 secs, 0.0539 secs
  resp wait:    0.2519 secs, 0.0006 secs, 0.8879 secs
  resp read:    0.0000 secs, 0.0000 secs, 0.0801 secs

Status code distribution:
  [200] 100000 responses
```