# qr-attendance-system


---

## 🔐 Authentication Flow

1. User logs in using email & password  
2. Backend generates JWT token  
3. Token is stored in frontend (localStorage)  
4. Every request includes:
   ```
   Authorization: Bearer <token>
   ```
5. JWT filter validates request before processing

---

## 📲 QR Attendance Flow

1. Admin generates QR for a class
2. QR contains session token
3. Student scans QR
4. Attendance is validated in backend
5. Real-time update sent via WebSocket
6. Dashboard updates instantly

---

## 🔥 WebSocket Topics

- `/topic/attendance` → Live attendance updates

---

## ⚙️ API Endpoints

### Auth
```
POST /api/v1/auth/authenticate
POST /api/v1/auth/register
```

### Admin
```
GET /api/v1/admin/generate/{classId}
```

---

## 🧠 Key Learnings

- Secure JWT authentication flow
- WebSocket real-time communication
- Handling session-based QR logic
- Preventing duplicate active sessions
- Debugging 401 / 403 / 500 errors in Spring Boot

---

## 🧪 Future Improvements

- ⏰ Scheduled QR auto-expiry (cron job)
- 📱 Mobile app integration
- 📊 Admin analytics dashboard
- 🧾 Attendance export (Excel/PDF)
- 👨‍🏫 Multi-class support improvements

---

## 👨‍💻 Author

**Suman Kumar**  
Backend Developer (Spring Boot)

---

## ⭐ If you like this project
Give it a star ⭐ and feel free to contribute!
```

---

If you want, I can next:
- make **GitHub banner image**
- or **project architecture diagram**
- or **resume project description (ATS optimized)**

Just tell 👍
