## load test

```bash
hey -n 100000 -c 1000 -m GET http://localhost:8080/customers/1


Summary:
  Total:        22.6243 secs
  Slowest:      0.7051 secs
  Fastest:      0.0222 secs
  Average:      0.2249 secs
  Requests/sec: 4420.0246
  
  Total data:   7800000 bytes
  Size/request: 78 bytes

Response time histogram:
  0.022 [1]     |
  0.090 [312]   |
  0.159 [1062]  |■
  0.227 [69557] |■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
  0.295 [25951] |■■■■■■■■■■■■■■■
  0.364 [2438]  |■
  0.432 [189]   |
  0.500 [18]    |
  0.569 [78]    |
  0.637 [194]   |
  0.705 [200]   |


Latency distribution:
  10% in 0.2016 secs
  25% in 0.2046 secs
  50% in 0.2110 secs
  75% in 0.2419 secs
  90% in 0.2707 secs
  95% in 0.2825 secs
  99% in 0.3245 secs

Details (average, fastest, slowest):
  DNS+dialup:   0.0002 secs, 0.0222 secs, 0.7051 secs
  DNS-lookup:   0.0001 secs, 0.0000 secs, 0.0385 secs
  req write:    0.0001 secs, 0.0000 secs, 0.0739 secs
  resp wait:    0.2242 secs, 0.0030 secs, 0.6878 secs
  resp read:    0.0000 secs, 0.0000 secs, 0.0447 secs

Status code distribution:
  [200] 100000 responses
```