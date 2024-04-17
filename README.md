# Policy-Management-System

**About the System**
Policy Management System (PMS) is basically a system that helps to maintain
the Policies and Payments. It also allows to search a Policy and allow end users
to manage policies with ease.
The following section will cover aspects related to Bank Management System.
  **a) User Registration.
    b) Policy Registration.
    c) Search Policies.**

**User Registration** - User should submits the details into the system.

**Policy Registration** - Policy Registration be used by the admin to register the
policies. The system will generate the policy id and if it is a valid policy the
system will store the details in the database.

**Search Policies**- Search policies. The system will provide a search engine
mechanism to find the policies. This will access the database by getting the details
based on user search criteria

Rest APIs: \
**POST - /api/v1.0/customer/register** - Register a new user \
**POST - /api/v1.0/policy/register** -Register a new plocy \
**GET - /api/v1.0/policy/getall** -Fetches all the policy Details \
**GET - /api/v1.0/policy/ searches** -Fetches policy Detail based on Policy Type, Number years, Company of Name, Policy Id, Policy Name \
[POST /searches # create a new search \
GET /searches # list all searches]
