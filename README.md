# 🚖 Taxi Booking App – Part 7 (Day 39)

## 🔐 Admin Credential Update + Feedback Flow

This branch introduces a secure and user-friendly way for admins to **update their credentials** (username and password) and view feedback messages on the dashboard.

### ✅ Features Added

- Secure form to change admin username & password
- Validates old credentials before updating
- Uses `RedirectAttributes` to show success or error messages
- Displays message on the dashboard page after form submission

### 📂 Key Files

src/
├── controller/
│ └── AdminController.java // changeCredentials logic & redirection
├── templates/
│ └── admin/
│ ├── changecredentials.html // input form for credentials
│ └── dashboard.html // message shown here after redirect


### 🧠 What You'll Learn

- How to verify user credentials in the backend
- How to redirect with flash messages using Spring
- UX pattern: Always return to dashboard with message (clean flow)

### 💡 Example Messages

- ✅ `Credentials updated successfully`
- ❌ `Wrong old credentials`

### 🏃 How to Run

```bash
git clone https://github.com/bibekpandey0521/taxibooking.git
cd taxibooking
git checkout Part7
./mvnw spring-boot:run
