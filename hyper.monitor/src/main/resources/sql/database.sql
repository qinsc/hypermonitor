/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 12                       */
/* Created on:     2016/8/6 22:05:44                            */
/*==============================================================*/

drop table if exists HM_DISK;

drop table if exists HM_HOST;

drop table if exists HM_HOST_GROUP;

drop table if exists HM_NIC;

drop table if exists HM_TAG;

drop table if exists HM_TAG_RELATION;

/*==============================================================*/
/* Table: HM_DISK                                               */
/*==============================================================*/
create table HM_DISK 
(
   DISK_ID              varchar(64)                    not null,
   HOST_ID              varchar(64)                    null,
   DEVICE               varchar(100)                   null,
   PATH                 varchar(128)                   null,
   FSTYPE               varchar(32)                    null,
   OPTS                 varchar(128)                   null,
   constraint PK_HM_DISK primary key clustered (DISK_ID)
);

/*==============================================================*/
/* Table: HM_HOST                                               */
/*==============================================================*/
create table HM_HOST 
(
   HOST_ID              varchar(64)                    not null,
   GROUP_ID             varchar(64)                    null,
   HOST_NAME            varchar(64)                    null,
   OS                   varchar(100)                   null,
   OS_PLATFORM          varchar(100)                   null,
   PLATFORM_FAMILY      varchar(100)                   null,
   OS_PLATFORM_VERSION  varchar(100)                   null,
   CPU_CORES            integer                        null,
   CPU_THREADS          integer                        null,
   CPU_MODEL_NAME       varchar(100)                   null,
   CPU_MHZ              integer                        null,
   MEM_SIZE             integer                        null,
   constraint PK_HM_HOST primary key clustered (HOST_ID)
);

/*==============================================================*/
/* Table: HM_HOST_GROUP                                         */
/*==============================================================*/
create table HM_HOST_GROUP 
(
   GROUP_ID             varchar(64)                    not null,
   GROUP_NAME           varchar(64)                    null,
   GROUP_DESC           varchar(512)                   null,
   constraint PK_HM_HOST_GROUP primary key clustered (GROUP_ID)
);

/*==============================================================*/
/* Table: HM_NIC                                                */
/*==============================================================*/
create table HM_NIC 
(
   NIC_ID               varchar(64)                    not null,
   HOST_ID              varchar(64)                    null,
   NIC_NAME             varchar(64)                    null,
   MAC                  varchar(64)                    null,
   IP                   varchar(64)                    null,
   constraint PK_HM_NIC primary key clustered (NIC_ID)
);

/*==============================================================*/
/* Table: HM_TAG                                                */
/*==============================================================*/
create table HM_TAG 
(
   TAG_ID               varchar(64)                    not null,
   TAG_KEY              varchar(64)                    null,
   TAG_DESC             varchar(512)                   null,
   constraint PK_HM_TAG primary key clustered (TAG_ID)
);

/*==============================================================*/
/* Table: HM_TAG_RELATION                                       */
/*==============================================================*/
create table HM_TAG_RELATION 
(
   RELATION_ID          varchar(64)                    not null,
   TAG_ID               varchar(64)                    null,
   TAG_VALUE            varchar(256)                   null,
   OWNER_ID             varchar(64)                    not null,
   constraint PK_HM_TAG_RELATION primary key clustered (RELATION_ID)
);


