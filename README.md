Spring Security:
-----------------
Spring Security is a powerful and highly customizable authentication and access control framework. Spring Security is a framework that focuses on providing both  authentication and authorization to Java applications. Like all Spring projects, the real power
of Spring Security is found in how easily it can be extended to meet custom requirements. It  overcomes all the problems that come during creating non spring security applications and  manage new server environment for the application. The main goal of Spring Security is to
make it easy to add security features to your applications. It follows a modular design,  allowing you to choose and configure various components according to your specific  requirements. Some of the key features of Spring Security include:

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
What is Authentication?
Authentication in Spring refers to the process of verifying the identity of a user or client accessing a system or application. It is a crucial aspect of building secure applications
to ensure that only authorized individuals can access protected resources.
In the context of Spring Security, authentication involves validating the credentials provided by the user and establishing their identity. Spring Security offers various authentication mechanisms and supports integration with different authentication providers.

Here's a high-level overview of how authentication works in Spring Security:

User provides credentials: The user typically provides credentials, such as a username and password, in order to authenticate themselves.
Authentication request: The application receives the user's credentials and creates an authentication request object.

Authentication manager:
The authentication request is passed to the authentication manager, which is responsible for validating the credentials and performing the authentication process.

Authentication provider:
The authentication manager delegates the actual authentication process to one or more authentication providers. An authentication provider is responsible
for verifying the user's credentials against a specific authentication mechanism, such as a user database, LDAP server, or OAuth provider.

Authentication result:
The authentication provider returns an authentication result, indicating whether the user's credentials were successfully authenticated or not. If
successful, the result typically contains the authenticated user details, such as the username and granted authorities. Security context: If the authentication is successful, Spring Security establishes a security
context for the authenticated user. The security context holds the user's authentication details and is associated with the current thread.

Access control:
With the user authenticated, Spring Security can enforce access control policies based on the user's granted authorities or other attributes. This allows the
application to restrict access to certain resources or operations based on the user's role or permissions.
Spring Security provides several authentication mechanisms out-of-the-box, including form based authentication, HTTP Basic/Digest authentication, JWT token, OAuth-based authentication. Spring also supports customization and extension, allowing you to integrate
with your own authentication providers or implement custom authentication logic to meet your specific requirements.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
What is Authorization?

Authorization, also known as access control, is the process of determining what actions or resources a user or client is allowed to access within a system or application. It involves enforcing permissions and restrictions based on the user's identity, role, or other
attributes. Once a user is authenticated, authorization is used to control their access to
different parts of the application and its resources.

Here are the key concepts related to authorization in Spring Security:

Roles: Roles represent a set of permissions or privileges granted to a user. They define the user's high-level responsibilities or functional areas within the application. For example, an
application may have roles such as "admin," "user," or "manager."
Permissions:
Permissions are specific actions or operations that a user is allowed to perform. They define the granular level of access control within the application. For example, a user with the "admin" role may have permissions to create, update, and delete resources, while a user with the "user" role may only have read permissions.
Security Interceptors: Spring Security uses security interceptors to enforce authorization rules.
These interceptors are responsible for intercepting requests and checking whether the user has the required permissions to access the requested resource. They can be configured
to protect URLs, methods, or other parts of the application.
Role-Based Access Control (RBAC): RBAC is a common authorization model in which access control is based on roles. Users are assigned roles, and permissions are associated with those roles. Spring Security supports RBAC by allowing you to define roles and assign them
to users.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
What is a JWT?
JSON Web Token is an open industry standard used to share information between  two entities, usually a client (like your app’s frontend) and a server (your app’s backend).
They contain JSON objects which have the information that needs to be shared. Each JWT is also signed using cryptography (hashing) to ensure that the JSON contents (also known as JWT claims) cannot be altered by the client or a malicious party.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Structure of a JWT:
-------------------------
A JWT contains three parts:

• Header: Consists of two parts:
o The signing algorithm that’s being used.
o The type of token, which, in this case, is mostly “JWT”.

• Payload: The payload contains the claims or the JSON object of clients.

• Signature: A string that is generated via a cryptographic algorithm that can be used to
verify the integrity of the JSON payload.
In general, whenever we generated token with JWT, token generated in the format of
<header>.<payload>.<signature>  in side JWT.
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
We are using Java JWT API for creation and validation of Tokens.

• Create A Maven Project

• Add Below both dependencies, required for java JWT API.

<dependencies>
<dependency>
<groupId>io.jsonwebtoken</groupId>
<artifactId>jjwt</artifactId>
<version>0.9.1</version>
</dependency>

<dependency>
<groupId>javax.xml.bind</groupId>
<artifactId>jaxb-api</artifactId>
<version>2.3.0</version>
</dependency>
</dependencies>
