// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

final class MyJSONParser implements JSONParser {



  @Override
  public JSON parse(String in) throws IOException {
    Scanner scanner = new Scanner(in);
    MyJSON parsedJSON = new MyJSON();
    Stack<String> items = new Stack<>();
    JSON curr = parsedJSON;
    String name;

    if (scanner.hasNext("{")) {
      items.push(scanner.next("{"));
    }
    if (scanner.hasNext("\"")) {
        name = scanner.next();
        parsedJSON.setObject(name, null);
      }
    while (scanner.hasNext()) {
      if (scanner.hasNext("{")) {
        items.push(scanner.next("{"));
        curr = curr.getObject(name);
      } else if (scanner.hasNext("\"")) {
        name = scanner.next();
        curr.setObject(name, null);
      } else if (scanner.hasNext(":")) {
        scanner.next();
      } else if (scanner.hasNext("}")){
        items.pop();
      } else {
        throw new IOException("Invalid Character in JSON");
      }
     }
     if (!items.isEmpty()) {
      throw new IOException("Uneven Brackets in JSON");
     }
     return parsedJSON;
   }
}
