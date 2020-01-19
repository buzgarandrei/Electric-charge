package com.diver6ty.chargetapbackend.exceptions

import java.lang.Exception

class AppointmentNotFoundException: Exception("Appointment with given ID not found")