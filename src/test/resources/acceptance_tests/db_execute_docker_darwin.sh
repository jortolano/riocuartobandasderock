#Darwing 64 bit implementation of db_execute.sh

#This scripts take a string representing an database query
#and it will append the query in a log file and redirect the error stream to an error log file

#1 : The query to execute

psql -h 192.168.99.100 -p 5432 -U rock_db_owner -d rcrockbands -c "$1" > sql_log_out.txt 2> sql_log_err.txt
