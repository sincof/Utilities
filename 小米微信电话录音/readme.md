# 小米微信电话录音

根绝网友的信息，最早在 miui12.0.1 就有微信电话录音, 我之前没有关注过这件事，自从被坑了后，就开始注意保存录音资料

我的机型是红米 k20pro(miui 12.5.6 最后的正式版)，在下面的路径就可以开启

小米微信录音有些机型是在 设置 -> 应用设置 -> 系统应用设置 -> 录音机 -> 应用通话录音 就可以开启

但是有些机型在上面的路径里面是无法查看到的，怎么办？

## 思路

小米手机的应用包都是一样的，说明功能都是具有的，只是某些机型可能无法使用, 不稳定, 就没有显示这个 activity，怎么做？

### 如何查看那个的 activity

1. 参考别人的, 进入那个 activity 后, 电脑通过 adb 连接上手机后, 通过下面的命令可以查看当前的在的应用的 activity (首先要保证你的系统路径中存在 adb,否则要去系统环境设置中设置相应的路径)

```shell
adb shell
logcat | grep ActivityManager
# 应该可以得到是这个activity com.miui.audiomonitor/.VoipRecordSettingsActivity
```

2. 我的蠢方法

```shell
# 通过adb查看所有包的activity
.\adb.exe shell dumpsys package > bb.txt
# 然后 搜索 com.miui.audiomonitor 可以找到一些相关的activity
# 然后一个个去尝试 发现com.miui.audiomonitor/.VoipRecordSettingsActivity是相关的activity
# 进了shell后,可以am通过start去启动相应的activity, 下面的这个activity就是对应的
adb shell
am start -n com.miui.audiomonitor/.VoipRecordSettingsActivity
```
