name := "DrivingApp"

version := "1.0"

proguardOptions += keepMain("DrivingApp")

proguardDefaultArgs := Seq("-dontwarn", "-dontobfuscate")