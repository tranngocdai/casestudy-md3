CREATE database CaseStudyModule3;
use CaseStudyModule3;
create table role(
                     id_role int auto_increment primary key,
                     name_role varchar(255) not null
);
create table users(
                      id_user int auto_increment primary key,
                      fullName varchar(255) not null,
                      phone varchar(255) not null,
                      username varchar(255) not null,
                      password varchar(255) not null,
                      id_role int not null,
                      foreign key (id_role) references role(id_role)
);

create table icon(
                     id_icon int auto_increment primary key,
                     link_icon varchar(255) not null
);

create table revenue_Categories(
                                  id_rc int auto_increment primary key,
                                  name_rc varchar(255) not null,
                                  id_icon int not null,
                                  id_user int not null,
                                  foreign key (id_icon) references icon(id_icon),
                                  foreign key (id_user) references users(id_user)
);
create table expenditure_Categories(
                                      id_ec int auto_increment primary key,
                                      name_ec varchar(255) not null,
                                      id_icon int not null,
                                      id_user int not null,
                                      foreign key (id_icon) references icon(id_icon),
                                      foreign key (id_user) references users(id_user)
);
create table revenue(
                        id_re int auto_increment primary key,
                        name_re varchar(255) not null,
                        date_re date not null,
                        money_re int not null,
                        note varchar(255) not null,
                        id_rc int not null,
                        foreign key (id_rc) references revenue_Categories(id_rc)
);
create table expenditure(
                            id_ex int auto_increment primary key,
                            name_ex varchar(255) not null,
                            date_ex date not null,
                            money_ex int not null,
                            note varchar(255) not null,
                            id_ec int not null,
                            foreign key (id_ec) references expenditure_Categories(id_ec)
);
SELECT * FROM revenue join rc on revenue.id_rc = revenueCategories.id_revenueCategories
Where id_user =?;