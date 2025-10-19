create table stq_STQEmployee (
	employeeId LONG not null primary key,
	companyId LONG,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	email VARCHAR(75) null,
	jobTitle VARCHAR(75) null,
	phoneNumber VARCHAR(75) null,
	companyName VARCHAR(75) null,
	department VARCHAR(75) null,
	workLocation VARCHAR(100) null
);