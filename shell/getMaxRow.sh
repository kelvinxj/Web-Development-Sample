#!/bin/bash

#DB2Home
DB2_HOME=/opt/IBM/db2/V10.1_FP4

#Database Name
DATABASE_NAME=MDMPRDDB
DATABASE_SCHEMA=mdmuser1

dbUser=cn06689a
dbPwd=Lihui2024

query_sql="select count(distinct contact.cont_id)from(select s.cont_idfrom suspect swhere S.person_org_code = 'P'and inactivated_dt is nulland susp_st_tp_cd not in(6,26)and cur_suspect_tp_cd = 1and exists(select 1 from contact cwhere s.cont_id = c.cont_idand c.inactivated_dt is null)and exists(select 1 from contact cwhere s.suspect_cont_id = c.cont_idand c.inactivated_dt is null)union allselect s.suspect_cont_idfrom suspect swhere S.person_org_code = 'P'and inactivated_dt is nulland susp_st_tp_cd not in(6,26)and cur_suspect_tp_cd = 1and exists(select 1 from contact cwhere s.cont_id = c.cont_idand c.inactivated_dt is null)and exists(select 1 from contact cwhere s.suspect_cont_id = c.cont_idand c.inactivated_dt is null))contact with ur;"

DB2_HOME/bin/db2 connect to $DATABASE_NAME user $dbUser using $dbPwd
DB2_HOME/bin/db2 set schema $DATABASE_SCHEMA
DB2_HOME/bin/db2 $query_sql > ./rowCount.txt
