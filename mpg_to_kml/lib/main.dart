import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(home: MyApp()));
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  double defaultMargin = 10.0;

  final String kmlText = 'Km/l';
  final String mpgText = 'Mpg';

  String _userInput;
  String result = '0';
  String textHint;

  bool kmlToMpg = true;

  void _switchConversor() {
    setState(() {
      this.kmlToMpg = !kmlToMpg;
    });

    this._calculate();
  }

  void _calculate() {
    double resultDouble = 0;

    if ((this._userInput != null) && (this._userInput.trim() != '')) {
      var userInputDouble = double.parse(this._userInput);

      resultDouble = this.kmlToMpg ? (userInputDouble * 2.3521458329476) : (userInputDouble * 0.4251437075);
    }

    setState(() {
      this.result = resultDouble.toStringAsFixed(2);
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primaryColor: Colors.deepPurple,
        accentColor: Colors.orange,
      ),
      home: Scaffold(
        floatingActionButton: FloatingActionButton(
          onPressed: this._switchConversor,
          child: Icon(
            Icons.swap_vert,
            color: Colors.white,
          ),
          backgroundColor: Colors.deepPurple,
        ),
        resizeToAvoidBottomPadding: true,
        backgroundColor: Colors.white,
        body: Container(
          child: Stack(
            children: <Widget>[
              Center(
                child: SingleChildScrollView(
                  child: Column(
                    children: <Widget>[
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                          Container(
                            width: 80,
                            margin: EdgeInsets.only(
                                top: 100,
                                right: defaultMargin,
                                left: defaultMargin,
                                bottom: defaultMargin),
                            child: TextFormField(
                              textAlign: TextAlign.center,
                              cursorColor: Colors.deepPurple,
                              autofocus: true,
                              keyboardType: TextInputType.number,
                              onChanged: (text) {
                                this._userInput = text;
                                this._calculate();
                              },
                              maxLength: 10,
                              decoration: InputDecoration(
                                  filled: true,
                                  hintText: kmlText,
                                  fillColor: Colors.deepPurple.shade50),
                            ),
                          ),
                          Container(
                            margin:
                                EdgeInsets.only(top: 100, right: defaultMargin),
                            child: Text(
                              this.kmlToMpg ? this.kmlText : this.mpgText,
                              style: TextStyle(color: Colors.grey),
                            ),
                          ),
                        ],
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                          Container(
                            margin: EdgeInsets.only(
                              top: defaultMargin,
                              left: defaultMargin,
                              right: defaultMargin,
                            ),
                            child: Text(
                              this.result,
                              style: TextStyle(fontSize: 21),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(
                                top: defaultMargin, right: defaultMargin),
                            child: Text(
                              this.kmlToMpg ? this.mpgText : this.kmlText,
                              style: TextStyle(color: Colors.grey),
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
