volatile int count = 0;
const int buttonPin = 2;
int buttonState;
int lastButtonState = LOW;

unsigned long lastDebounceTime = 0;
unsigned long debounceDelay = 50; 

void buttonPressed() {
 
  if((millis() - lastDebounceTime) > debounceDelay){
    count+= 1;
    Serial.println(count);
    lastDebounceTime = millis();
  }

  
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, RISING);
  Serial.begin(9600);
}

void loop() {
  
}
