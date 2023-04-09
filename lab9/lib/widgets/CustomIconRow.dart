import 'package:flutter/material.dart';

class SocialMediaIconsRow extends StatelessWidget {
  final Function onGoogleLogin;
  final Function onFacebookLogin;
  final Function onAppleLogin;

  const SocialMediaIconsRow({super.key,
    required this.onGoogleLogin,
    required this.onFacebookLogin,
    required this.onAppleLogin,
  });

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        IconButton(
          onPressed: () {
            onGoogleLogin();
          },
          icon: Image.asset(
            'assets/img/google_logo.png',
            height: 100,
          ),
        ),
        IconButton(
          onPressed: () {
            onFacebookLogin();
          },
          icon: Image.asset(
            'assets/img/facebook_logo.png',
            height: 100,
          ),
        ),
        IconButton(
          onPressed: () {
            onAppleLogin();
          },
          icon: Image.asset(
            'assets/img/apple_logo.png',
            height: 100,
          ),
        ),
      ],
    );
  }
}
