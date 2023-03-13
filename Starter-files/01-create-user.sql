CREATE USER 'ecommerceplugin'@'localhost' IDENTIFIED BY 'ecommerceapp';

GRANT ALL PRIVILEGES ON * . * TO 'ecommerceplugin'@'localhost';

# See the MySQL Reference Manual for details: 
# https://dev.mysql.com/doc/refman/8.0/en/caching-sha2-pluggable-authentication.html
#
ALTER USER 'ecommerceplugin'@'localhost' IDENTIFIED WITH mysql_native_password BY 'ecommerceplugin';