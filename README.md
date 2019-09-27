# 2FA 2Furious

Many 2-factor auth apps use HOTP or TOTP algorithms.

## HOTP

Hash-based One-Time Password: The number is generated from a hash of a shared secret key and
a counter. The counter must be kept in sync between the client and server, which can be hard
to do and there's no recovery except an out-of-band resynchronization. For that reason most
services use TOTP.

## TOTP

Time-based One-Time Password: The one-time passwords are generated from a hash of the shared
secret key and the current time block - ie a counter that increments every 30s. As long as the
client and server clocks are roughly synced this will be good enough although you can add 
a window to your verification to be more lenient to wrong clocks, at a cost of being easier
to brute-force.


Java code demonstrating both using [JOTP](https://github.com/amdelamar/jotp) is in `Demos.java`.