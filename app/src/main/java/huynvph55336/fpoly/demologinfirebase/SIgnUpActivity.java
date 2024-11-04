package huynvph55336.fpoly.demologinfirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SIgnUpActivity extends AppCompatActivity {
    Button btnRegister;
    EditText edEmail, edPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        // Khởi tạo FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Khởi tạo các view
        btnRegister = findViewById(R.id.btnReginters);
        edEmail = findViewById(R.id.edemails);
        edPassword = findViewById(R.id.edpasswords);

        // Thiết lập padding cho layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Thiết lập sự kiện click cho nút Đăng ký
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();

                // Kiểm tra nếu email và password không rỗng
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SIgnUpActivity.this, "Email và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo tài khoản mới với email và password
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SIgnUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SIgnUpActivity.this, "Tạo mới thành công", Toast.LENGTH_SHORT).show();
                                    // Có thể thêm Intent chuyển đến Activity khác sau khi tạo tài khoản thành công
                                } else {
                                    Toast.makeText(SIgnUpActivity.this, "Tạo mới không thành công: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
