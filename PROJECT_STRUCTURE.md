# Project structure

**Backend (this folder)**  
This folder is the Spring Boot API only. Run with: `mvn spring-boot:run`

**Frontend (separate folder)**  
The frontend lives in a **sibling** folder:

```
IdeaProjects/
├── Blog-Backend/     ← you are here (backend only)
└── blog-frontend/    ← frontend app
```

To run the frontend: open `blog-frontend`, run `npm install` then `npm run dev`.

If you still see a `blog-frontend` folder inside this backend folder, you can delete it after closing any tools that use it (IDE, terminal running `npm run dev`). The real frontend is in the sibling `blog-frontend` folder.
