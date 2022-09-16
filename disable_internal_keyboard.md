1. Ubuntu internal keyboard
  网上常用方法是通过`xinput list`获取设备列表，然后找到开头为AT的设备名，再用禁用`xinput set-prop 18 "Device_Name" 0` 启用`xinput set-prop 18 "Device_Name" 1`
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
