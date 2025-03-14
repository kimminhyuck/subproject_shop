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
   │  │  │           │  ├─ RefreshToken.java
   │  │  │           │  └─ User.java
   │  │  │           ├─ repository
   │  │  │           │  ├─ AuthRepository.java
   │  │  │           │  ├─ RefreshTokenRepository.java
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
   │  │  │           │  ├─ AuthService.java
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
      │           │  ├─ RefreshToken$RefreshTokenBuilder.class
      │           │  ├─ RefreshToken.class
      │           │  ├─ User$UserBuilder.class
      │           │  └─ User.class
      │           ├─ repository
      │           │  ├─ AuthRepository.class
      │           │  ├─ RefreshTokenRepository.class
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
      │           │  ├─ AuthService.class
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
│  │  ├─ assets
│  │  │  ├─ background
│  │  │  │  ├─ overlay.jpg
│  │  │  │  └─ shape-square.svg
│  │  │  ├─ icons
│  │  │  │  ├─ flags
│  │  │  │  │  ├─ ic-flag-de.svg
│  │  │  │  │  ├─ ic-flag-en.svg
│  │  │  │  │  └─ ic-flag-fr.svg
│  │  │  │  ├─ glass
│  │  │  │  │  ├─ ic-glass-bag.svg
│  │  │  │  │  ├─ ic-glass-buy.svg
│  │  │  │  │  ├─ ic-glass-message.svg
│  │  │  │  │  └─ ic-glass-users.svg
│  │  │  │  ├─ navbar
│  │  │  │  │  ├─ ic-analytics.svg
│  │  │  │  │  ├─ ic-blog.svg
│  │  │  │  │  ├─ ic-cart.svg
│  │  │  │  │  ├─ ic-disabled.svg
│  │  │  │  │  ├─ ic-lock.svg
│  │  │  │  │  └─ ic-user.svg
│  │  │  │  ├─ notification
│  │  │  │  │  ├─ ic-notification-chat.svg
│  │  │  │  │  ├─ ic-notification-mail.svg
│  │  │  │  │  ├─ ic-notification-package.svg
│  │  │  │  │  └─ ic-notification-shipping.svg
│  │  │  │  ├─ shape-avatar.svg
│  │  │  │  └─ workspaces
│  │  │  │     ├─ logo-1.webp
│  │  │  │     ├─ logo-2.webp
│  │  │  │     └─ logo-3.webp
│  │  │  ├─ illustrations
│  │  │  │  ├─ illustration-404.svg
│  │  │  │  └─ illustration-dashboard.webp
│  │  │  └─ images
│  │  │     ├─ avatar
│  │  │     │  ├─ avatar-1.webp
│  │  │     │  ├─ avatar-10.webp
│  │  │     │  ├─ avatar-11.webp
│  │  │     │  ├─ avatar-12.webp
│  │  │     │  ├─ avatar-13.webp
│  │  │     │  ├─ avatar-14.webp
│  │  │     │  ├─ avatar-15.webp
│  │  │     │  ├─ avatar-16.webp
│  │  │     │  ├─ avatar-17.webp
│  │  │     │  ├─ avatar-18.webp
│  │  │     │  ├─ avatar-19.webp
│  │  │     │  ├─ avatar-2.webp
│  │  │     │  ├─ avatar-20.webp
│  │  │     │  ├─ avatar-21.webp
│  │  │     │  ├─ avatar-22.webp
│  │  │     │  ├─ avatar-23.webp
│  │  │     │  ├─ avatar-24.webp
│  │  │     │  ├─ avatar-25.webp
│  │  │     │  ├─ avatar-3.webp
│  │  │     │  ├─ avatar-4.webp
│  │  │     │  ├─ avatar-5.webp
│  │  │     │  ├─ avatar-6.webp
│  │  │     │  ├─ avatar-7.webp
│  │  │     │  ├─ avatar-8.webp
│  │  │     │  └─ avatar-9.webp
│  │  │     ├─ cover
│  │  │     │  ├─ cover-1.webp
│  │  │     │  ├─ cover-10.webp
│  │  │     │  ├─ cover-11.webp
│  │  │     │  ├─ cover-12.webp
│  │  │     │  ├─ cover-13.webp
│  │  │     │  ├─ cover-14.webp
│  │  │     │  ├─ cover-15.webp
│  │  │     │  ├─ cover-16.webp
│  │  │     │  ├─ cover-17.webp
│  │  │     │  ├─ cover-18.webp
│  │  │     │  ├─ cover-19.webp
│  │  │     │  ├─ cover-2.webp
│  │  │     │  ├─ cover-20.webp
│  │  │     │  ├─ cover-21.webp
│  │  │     │  ├─ cover-22.webp
│  │  │     │  ├─ cover-23.webp
│  │  │     │  ├─ cover-24.webp
│  │  │     │  ├─ cover-3.webp
│  │  │     │  ├─ cover-4.webp
│  │  │     │  ├─ cover-5.webp
│  │  │     │  ├─ cover-6.webp
│  │  │     │  ├─ cover-7.webp
│  │  │     │  ├─ cover-8.webp
│  │  │     │  └─ cover-9.webp
│  │  │     ├─ minimal-free-preview.jpg
│  │  │     └─ product
│  │  │        ├─ product-1.webp
│  │  │        ├─ product-10.webp
│  │  │        ├─ product-11.webp
│  │  │        ├─ product-12.webp
│  │  │        ├─ product-13.webp
│  │  │        ├─ product-14.webp
│  │  │        ├─ product-15.webp
│  │  │        ├─ product-16.webp
│  │  │        ├─ product-17.webp
│  │  │        ├─ product-18.webp
│  │  │        ├─ product-19.webp
│  │  │        ├─ product-2.webp
│  │  │        ├─ product-20.webp
│  │  │        ├─ product-21.webp
│  │  │        ├─ product-22.webp
│  │  │        ├─ product-23.webp
│  │  │        ├─ product-24.webp
│  │  │        ├─ product-3.webp
│  │  │        ├─ product-4.webp
│  │  │        ├─ product-5.webp
│  │  │        ├─ product-6.webp
│  │  │        ├─ product-7.webp
│  │  │        ├─ product-8.webp
│  │  │        └─ product-9.webp
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
   │  │  │           │  ├─ RefreshToken.java
   │  │  │           │  └─ User.java
   │  │  │           ├─ repository
   │  │  │           │  ├─ AuthRepository.java
   │  │  │           │  ├─ RefreshTokenRepository.java
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
   │  │  │           │  ├─ AuthService.java
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
      │           │  ├─ RefreshToken$RefreshTokenBuilder.class
      │           │  ├─ RefreshToken.class
      │           │  ├─ User$UserBuilder.class
      │           │  └─ User.class
      │           ├─ repository
      │           │  ├─ AuthRepository.class
      │           │  ├─ RefreshTokenRepository.class
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
      │           │  ├─ AuthService.class
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