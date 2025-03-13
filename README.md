
```
subproject_shop
├─ client
│  ├─ package-lock.json
│  ├─ package.json
│  ├─ public
│  │  ├─ favicon.ico
│  │  ├─ index.html
│  │  ├─ logo192.png
│  │  ├─ logo512.png
│  │  ├─ manifest.json
│  │  └─ robots.txt
│  ├─ README.md
│  └─ src
│     ├─ api
│     │  ├─ auth
│     │  │  └─ userService.js
│     │  └─ axiosInstance.js
│     ├─ App.css
│     ├─ App.js
│     ├─ App.test.js
│     ├─ index.css
│     ├─ index.js
│     ├─ logo.svg
│     ├─ pages
│     │  └─ UserList.js
│     ├─ reportWebVitals.js
│     └─ setupTests.js
└─ server
   ├─ .mvn
   │  └─ wrapper
   │     └─ maven-wrapper.properties
   ├─ HELP.md
   ├─ mvnw
   ├─ mvnw.cmd
   ├─ pom.xml
   ├─ src
   │  ├─ main
   │  │  ├─ java
   │  │  │  └─ com
   │  │  │     └─ shop
   │  │  │        └─ subshop
   │  │  │           ├─ config
   │  │  │           │  └─ WebConfig.java
   │  │  │           ├─ controller
   │  │  │           │  ├─ AuthController.java
   │  │  │           │  └─ UserController.java
   │  │  │           ├─ model
   │  │  │           │  └─ User.java
   │  │  │           ├─ repository
   │  │  │           │  └─ UserRepository.java
   │  │  │           ├─ security
   │  │  │           │  ├─ handler
   │  │  │           │  │  └─ OAuth2SuccessHandler.java
   │  │  │           │  ├─ JwtUtil.java
   │  │  │           │  └─ SecurityConfig.java
   │  │  │           ├─ service
   │  │  │           │  └─ UserService.java
   │  │  │           └─ SubshopApplication.java
   │  │  └─ resources
   │  │     ├─ application.properties
   │  │     ├─ static
   │  │     └─ templates
   │  └─ test
   │     └─ java
   │        └─ com
   │           └─ shop
   │              └─ subshop
   │                 └─ SubshopApplicationTests.java
   └─ target
      ├─ classes
      │  ├─ application.properties
      │  └─ com
      │     └─ shop
      │        └─ subshop
      │           ├─ config
      │           │  ├─ WebConfig$1.class
      │           │  └─ WebConfig.class
      │           ├─ controller
      │           │  ├─ AuthController.class
      │           │  └─ UserController.class
      │           ├─ model
      │           │  └─ User.class
      │           ├─ repository
      │           │  └─ UserRepository.class
      │           ├─ security
      │           │  ├─ handler
      │           │  │  └─ OAuth2SuccessHandler.class
      │           │  ├─ JwtUtil.class
      │           │  └─ SecurityConfig.class
      │           ├─ service
      │           │  └─ UserService.class
      │           └─ SubshopApplication.class
      ├─ generated-sources
      │  └─ annotations
      ├─ generated-test-sources
      │  └─ test-annotations
      └─ test-classes
         └─ com
            └─ shop
               └─ subshop
                  └─ SubshopApplicationTests.class

```
```
subproject_shop
├─ client
│  ├─ package-lock.json
│  ├─ package.json
│  ├─ public
│  │  ├─ favicon.ico
│  │  ├─ index.html
│  │  ├─ logo192.png
│  │  ├─ logo512.png
│  │  ├─ manifest.json
│  │  └─ robots.txt
│  ├─ README.md
│  ├─ src
│  │  ├─ api
│  │  │  ├─ auth
│  │  │  │  ├─ auth.js
│  │  │  │  └─ index.js
│  │  │  ├─ axios.js
│  │  │  ├─ axiosInstance.js
│  │  │  ├─ notification
│  │  │  │  └─ notificationService.js
│  │  │  └─ socket
│  │  │     └─ socket.js
│  │  ├─ App.css
│  │  ├─ App.js
│  │  ├─ App.test.js
│  │  ├─ auth
│  │  │  ├─ FindUserId.js
│  │  │  ├─ ForgotPassword.js
│  │  │  ├─ index.js
│  │  │  ├─ Login.js
│  │  │  ├─ Register.js
│  │  │  ├─ ResetPassword.js
│  │  │  └─ style
│  │  │     ├─ components
│  │  │     │  ├─ Content.tsx
│  │  │     │  ├─ CustomIcons.tsx
│  │  │     │  ├─ FindUserId.tsx
│  │  │     │  ├─ ForgotPassword.tsx
│  │  │     │  ├─ RegisterCard.tsx
│  │  │     │  └─ SignInCard.tsx
│  │  │     └─ theme
│  │  │        ├─ AppTheme.tsx
│  │  │        ├─ ColorModeIconDropdown.tsx
│  │  │        ├─ ColorModeSelect.tsx
│  │  │        ├─ customizations
│  │  │        │  ├─ dataDisplay.tsx
│  │  │        │  ├─ feedback.tsx
│  │  │        │  ├─ inputs.tsx
│  │  │        │  ├─ navigation.tsx
│  │  │        │  └─ surfaces.ts
│  │  │        └─ themePrimitives.ts
│  │  ├─ index.css
│  │  ├─ index.js
│  │  ├─ logo.svg
│  │  ├─ pages
│  │  ├─ reportWebVitals.js
│  │  ├─ routes
│  │  │  ├─ PrivateRoute.js
│  │  │  └─ store
│  │  │     ├─ authStore.js
│  │  │     └─ notificationStore.js
│  │  └─ setupTests.js
│  └─ tsconfig.json
├─ README.md
└─ server
   ├─ .mvn
   │  └─ wrapper
   │     └─ maven-wrapper.properties
   ├─ HELP.md
   ├─ mvnw
   ├─ mvnw.cmd
   ├─ pom.xml
   ├─ src
   │  ├─ main
   │  │  ├─ java
   │  │  │  └─ com
   │  │  │     └─ shop
   │  │  │        └─ subshop
   │  │  │           ├─ config
   │  │  │           │  └─ WebConfig.java
   │  │  │           ├─ controller
   │  │  │           │  ├─ AuthController.java
   │  │  │           │  └─ UserController.java
   │  │  │           ├─ model
   │  │  │           │  └─ User.java
   │  │  │           ├─ repository
   │  │  │           │  └─ UserRepository.java
   │  │  │           ├─ security
   │  │  │           │  ├─ filter
   │  │  │           │  │  └─ JwtAuthenticationFilter.java
   │  │  │           │  ├─ handler
   │  │  │           │  │  └─ OAuth2SuccessHandler.java
   │  │  │           │  ├─ JwtUtil.java
   │  │  │           │  ├─ SecurityConfig.java
   │  │  │           │  └─ UserDetailsServiceImpl.java
   │  │  │           ├─ service
   │  │  │           │  └─ UserService.java
   │  │  │           └─ SubshopApplication.java
   │  │  └─ resources
   │  │     ├─ application.properties
   │  │     ├─ static
   │  │     └─ templates
   │  └─ test
   │     └─ java
   │        └─ com
   │           └─ shop
   │              └─ subshop
   │                 └─ SubshopApplicationTests.java
   └─ target
      ├─ classes
      │  ├─ application.properties
      │  └─ com
      │     └─ shop
      │        └─ subshop
      │           ├─ config
      │           │  ├─ WebConfig$1.class
      │           │  └─ WebConfig.class
      │           ├─ controller
      │           │  ├─ AuthController.class
      │           │  └─ UserController.class
      │           ├─ model
      │           │  ├─ User$UserBuilder.class
      │           │  └─ User.class
      │           ├─ repository
      │           │  └─ UserRepository.class
      │           ├─ security
      │           │  ├─ filter
      │           │  │  └─ JwtAuthenticationFilter.class
      │           │  ├─ handler
      │           │  │  └─ OAuth2SuccessHandler.class
      │           │  ├─ JwtUtil.class
      │           │  ├─ SecurityConfig.class
      │           │  └─ UserDetailsServiceImpl.class
      │           ├─ service
      │           │  └─ UserService.class
      │           └─ SubshopApplication.class
      ├─ generated-sources
      │  └─ annotations
      ├─ generated-test-sources
      │  └─ test-annotations
      └─ test-classes
         └─ com
            └─ shop
               └─ subshop
                  └─ SubshopApplicationTests.class

```