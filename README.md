# pessimistic_optimistic_locking


apt-get update && apt-get install -y apache2-utils

ab -n 100 -c 10 -m POST http://host.docker.internal:8088/api/v1/products/27/pessimistic/reduce-stock
ab -n 100 -c 10 -m POST http://host.docker.internal:8088/api/v1/products/26/optimistic/reduce-stock
ab -n 100 -c 10 -m POST http://host.docker.internal:8088/api/v1/products/25/none/reduce-stock

-n 100: Toplam 100 istegi gonderir.
-c 10: Ayni anda 10 istegi calistirir.
-m POST: Istek turunu POST olarak belirtir.