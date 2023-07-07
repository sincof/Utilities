1. Ubuntu internal keyboard
  网上常用方法是通过`xinput list`获取设备列表，然后找到开头为AT的设备名，再用禁用`xinput set-prop 18 "Device_Name" 0` 启用`xinput set-prop 18 "Device_Name" 1` xinput list在很多上面是没有用的
  但是存在一个问题，就是可能没有以AT开头设备名
  可以直接通过下列方法禁用
  ```shell
    # i8042.nokbd 内核参数可以不检查内部键盘端口
    sudo vi /etc/default/grub
    # 找到 GRUB_CMDLINE_LINUX_DEFAULT="quiet splash" 添加参数
    GRUB_CMDLINE_LINUX_DEFAULT="quiet splash i8042.nokbd"
    sudo update-grub
    # 然后重启
    shutdown -r now
  ```



2. ArchLinux 禁用内置键盘
  先说明下我的配置情况, 用的是grub作为boot的管理系统,这样的话就是和ubuntu一样的过程,只是命令不一样
  ```shell
    # i8042.nokbd 内核参数可以不检查内部键盘端口
    sudo vi /etc/default/grub
    # 找到 GRUB_CMDLINE_LINUX_DEFAULT="quiet splash" 添加参数
    GRUB_CMDLINE_LINUX_DEFAULT="quiet i8042.nokbd"
    # 唯一不一样的地方就在于更新方式
    # 运行以下命令来重新生成 GRUB 配置文件：
    grub-mkconfig -o /boot/grub/grub.cfg
    # 运行以下命令更新引导加载程序（bootloader）：如果你使用 UEFI 引导方式：
    grub-install --target=x86_64-efi --efi-directory=/boot/efi --bootloader-id=GRUB
    # 传统的话 请将 /dev/sdX 替换为你的引导磁盘设备路径，例如 /dev/sda。
    grub-install --target=i386-pc /dev/sdX
  ```
  完成上述步骤后，你的 GRUB 配置文件将会被更新，并且引导加载程序会使用新的配置进行引导。重新启动你的系统，新的配置将生效。

  请注意，在更新 GRUB 配置之前，确保你对 /etc/default/grub 文件所做的修改是正确的，并且符合 GRUB 的配置语法。错误的配置可能导致引导问题。在修改配置文件之前，最好备份原始的 /etc/default/grub 文件，以便在需要时进行恢复。
4. windows internal keyboard

cmd 中运行这个就行
```cmd
sc config i8042prt start= disabled
```
