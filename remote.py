from firebase import firebase
import os

firebase = firebase.FirebaseApplication('https://epic-4c505.firebaseio.com/')


class Remote():
    def __init__(self):
        print("Booting...",end = "")
        self.refresh()
        print("Complete")
        
    def say(self):
        print("Brand: "+self.Brand)
        print("Type: "+self.Type)
        print("Version: "+str(self.Version))
        print("Channel: "+self.Channel)
        print("Direction: "+self.Direction)
        print("Menu: "+self.Menu)
        print("Mode: "+self.Mode)
        print("OK: "+self.OK)
        print("Power: "+self.Power)
        print("Source: "+self.Source)
        print("Temperature: "+self.Temperature)
        print("Volume: "+self.Volume)


    def hardwareRefresh(self):
        os.system("sudo cp /home/pi/"+self.Type+'_'+self.Brand+'_'+str(self.Version)+" /etc/lirc/lircd.conf")
        os.system("sudo /etc/init.d/lirc restart")
        os.system("sudo lircd --device /dev/lirc0")
    
    def refresh(self):
        Property = firebase.get('/TempUnit/CurrentRemote',None)
        dProperty = Property.get("Device")
        self.Type = dProperty.get("Type")
        self.Version = dProperty.get("Version")
        self.Brand = dProperty.get("Brand")

        self.Command = 'irsend SEND_ONCE '+self.Type+'_'+self.Brand+'_'+str(self.Version)+' '

        self.hardwareRefresh()
        
        sProperty = Property.get("Status")
        self.Channel = sProperty.get("Channel")
        self.Direction = sProperty.get("Direction")
        self.Menu = sProperty.get("Menu")
        self.Mode = sProperty.get("Mode")
        self.OK = sProperty.get("OK")
        self.Power = sProperty.get("Power")
        self.Source = sProperty.get("Source")
        self.Temperature = sProperty.get("Temperature")
        self.Volume = sProperty.get("Volume")

    def channelCheck(self):
        if(self.Channel == "Up"):
            print("Channel UP button pressed: "+ self.Command + 'KEY_CHANNELUP')
            os.system(self.Command + 'KEY_CHANNELUP')
        elif(self.Channel == "Down"):
            print("Channel DOWN button pressed: "+ self.Command + 'KEY_CHANNELDOWN')
            os.system(self.Command + 'KEY_CHANNELDOWN')

    def directionCheck(self):
        if(self.Direction == "Up"):
            print("UP button pressed: "+ self.Command + 'KEY_UP')
            os.system(self.Command + 'KEY_UP')
        elif(self.Direction == "Down"):
            print("DOWN button pressed: "+ self.Command + 'KEY_DOWN')
            os.system(self.Command + 'KEY_DOWN')
        elif(self.Direction == "Left"):
            print("LEFT button pressed: "+ self.Command + 'KEY_LEFT')
            os.system(self.Command + 'KEY_LEFT')
        elif(self.Direction == "Right"):
            print("RIGHT button pressed: "+ self.Command + 'KEY_RIGHT')
            os.system(self.Command + 'KEY_RIGHT')
            
    def menuCheck(self):
        if(self.Menu == "Clicked"):
            print("Menu button pressed: " + self.Command + 'KEY_MENU')
            os.system(self.Command + 'KEY_MENU')

    def modeCheck(self):
        if(self.Mode == "Clicked"):
            print("Mode button pressed: " + self.Command + 'KEY_MODE')
            os.system(self.Command + 'KEY_MODE')

    def okCheck(self):
        if(self.OK == "Clicked"):
            print("OK button pressed: " + self.Command + 'KEY_OK')
            os.system(self.Command + 'KEY_OK')
    
    def powerCheck(self):
        if(self.Power == "Clicked"):
            print("Power button pressed: " + self.Command + 'KEY_POWER')
            os.system(self.Command + 'KEY_POWER')

    def sourceCheck(self):
        if(self.Source == "Clicked"):
            print("Source button pressed: " + self.Command + 'KEY_VIDEO')
            os.system(self.Command + 'KEY_VIDEO')
            #Source == KEY_VIDEO

    def temperatureCheck(self):
        if(self.Temperature == "Up"):
            print("Temperature UP button pressed: "+ self.Command + 'KEY_UP')
            os.system(self.Command + 'KEY_UP')
        elif(self.Temperature == "Down"):
            print("Temperature DOWN button pressed: "+ self.Command + 'KEY_DOWN')
            os.system(self.Command + 'KEY_DOWN')
            #Temperature == KEY_UP/DOWN

    def volumeCheck(self):
        if(self.Volume == "Up"):
            print("Volume UP button pressed: "+ self.Command + 'KEY_VOLUMEUP')
            os.system(self.Command + 'KEY_VOLUMEUP')
        elif(self.Volume == "Down"):
            print("Volume DOWN button pressed: "+ self.Command + 'KEY_VOLUMEDOWN')
            os.system(self.Command + 'KEY_VOLUMEDOWN')

    def activeCheck(self):
        while(1):
            try:
                self.refresh()
                print("Clock")
                self.channelCheck()
                self.directionCheck()
                self.menuCheck()
                self.modeCheck()
                self.powerCheck()
                self.sourceCheck()
                self.temperatureCheck()
                self.volumeCheck()
                
            except:
                print("Connection Error : restarting")
                self.activeCheck()
                break

i = Remote()

i.activeCheck()
