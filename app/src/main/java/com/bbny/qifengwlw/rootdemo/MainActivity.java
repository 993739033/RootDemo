package com.bbny.qifengwlw.rootdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //当前应用的代码执行目录
        RootUtils.upgradeRootPermission(getPackageCodePath());
//        RootUtils.execRootCmd("cd /root/data");
//        RootUtils.execRootCmd("ls -a");
//        RootUtils.execRootCmd("ls -al");

//        try {
//            Runtime.getRuntime().exec(new String[]{"cd /test && ls -l"});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        RootUtils.execRootCmd("cd /test && ls -a");//这里必须连续使用
//        RootUtils.execRootCmd("cd /test && find  -mtime -1");//查找今天内有改动的文件
//        RootUtils.execRootCmd("find /test/ -newer /test/rbdbd.txt ");//查找文件夹中比文件还要新的文件
//         RootUtils.execRootCmd("find /test/ -name rr.txt");//查找文件名相匹配的文件
//        RootUtils.execRootCmd("find /test/ -size +10k");//才找文件size大于 12KB的文件，注意c表示byte
//        RootUtils.execRootCmd("cp  -a /test/rr.txt /test/rr11.txt");//复制文件1成文件2
//        RootUtils.execRootCmd("mv -f /test/rr.txt  ./");//强制移动rr.txt 文件到文件夹路径下
//        RootUtils.execRootCmd("rm -f /rr.txt");//强制删除文件  删除文件夹需使用递归删除
//        RootUtils.execRootCmd("ps  aux");//获取进程信息
//        RootUtils.execRootCmd("cat /test/rr11.txt");//获取文件中的信息、

//          chmod [-R] xyz 文件或目录
//         -R：进行递归的持续更改，即连同子目录下的所有文件都会更改
//         同时，chmod还可以使用u（user）、g（group）、o（other）、a（all）和+（加入）、-（删除）、=（设置）跟rwx搭配来对文件的权限进行更改

//        RootUtils.execRootCmd("chmod u-x /test/rr11.txt");//设置文件权限
//        RootUtils.execRootCmd("touch /test/rr22.txt");//以vim方式进行打开或创建 测试不出来 touch 可以创建一个新文件
//        RootUtils.execRootCmd("mkdir -p /test/A/B/c");//以mkdir 创建文件夹 使用-p 递归方式 创建文件夹
//        #追加文字到文件
//        cat >>/tmp/oldboy.txt << EOF
//        世界，你好！
//        EOF
//        RootUtils.execRootCmd("cat >>/test/rr22.txt << EOF 你好linux 编程 EOF");//追加文件
//        RootUtils.execRootCmd("ls -l");r

    }


    public void onSetting(View view) {
        final EditText text = findViewById(R.id.et_port);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                final String result = RootUtils.execRootCmd("/sbin/iptables -I INPUT -p tcp --dport" + text.getText().toString() + " -j ACCEPT");
//                RootUtils.execRootCmd("/etc/rc.d/init.d/iptables save");
//                RootUtils.execRootCmd("/etc/init.d/iptables restart");
//                RootUtils.execRootCmd("/sbin/iptables -L -n");
                //设置adb无线连接端口号
                RootUtils.execRootCmd("setprop service.adb.tcp.port "+text.getText().toString());
                RootUtils.execRootCmd("stop adbd");
                final String result = RootUtils.execRootCmd("start adbd");
                Log.d("rootDemo",result+"");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"Result:"+result,Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }
}
