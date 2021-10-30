import json
import re
import xmltodict
from enum import Enum
from timeit import Timer


class State(Enum):
    OPEN_TAG = 1
    CLOSE_TAG = 2
    CONTENT = 3


def write_value(stack, d, value):
    q = d
    for tag in stack[:len(stack) - 1]:
        q = q[tag]
        if isinstance(q, list):
            q = q[-1]
    if stack[-1] in q.keys() and q[stack[-1]] != {}:
        if not isinstance(q[stack[-1]], list):
            q[stack[-1]] = [q[stack[-1]]]
        q[stack[-1]].append(value)
    else:
        q[stack[-1]] = value


def first(xml_string):
    state = State.CONTENT
    s = ''
    stack = []
    d = {}
    for char in xml_string:
        if char == '<':
            if len(s.strip()) > 0:
                write_value(stack, d, s)
            state = State.OPEN_TAG
            s = ''
        elif char == '/' and state == State.OPEN_TAG:
            state = State.CLOSE_TAG
        elif char == '>':
            if state == State.OPEN_TAG:
                stack.append(s)
                write_value(stack, d, {})
            elif state == State.CLOSE_TAG:
                if stack[-1] == s:
                    stack.pop()
                else:
                    print("Not a valid xml")
                    exit()
            state = State.CONTENT
            s = ''
        else:
            s += char
    return json.dumps(d)


def second(xml_string):
    return json.dumps(xmltodict.parse(xml_string))


def third_rec(pattern, content):
    d = {}
    while True:
        match = pattern.search(content)
        if not match:
            break

        if match.group(1) in d.keys():
            if not isinstance(d[match.group(1)], list):
                d[match.group(1)] = [d[match.group(1)]]
            d[match.group(1)].append(third_rec(pattern, match.group(2)))
        else:
            d[match.group(1)] = third_rec(pattern, match.group(2))
        content = content.replace(match.group(0), '')

    if len(d) == 0:
        return content
    return d


def third(xml_string):
    pattern = re.compile(r'<(\w+?)>(.+?)</\1>')
    return json.dumps(third_rec(pattern, xml_string))


def fourth():
    setup = "xml_string = open('input.xml', 'r').read().replace('\\n', '')"
    print(Timer('from __main__ import first; first(xml_string)', setup=setup).timeit(10))
    print(Timer('from __main__ import second; second(xml_string)', setup=setup).timeit(10))
    print(Timer('from __main__ import third; third(xml_string)', setup=setup).timeit(10))


def fifth(xml_string):
    pass


def main():
    with open('input.xml', 'r') as f:
        xml_string = f.read().replace('\n', '')
    print(first(xml_string))
    print(second(xml_string))
    print(third(xml_string))
    fourth()


if __name__ == '__main__':
    main()
