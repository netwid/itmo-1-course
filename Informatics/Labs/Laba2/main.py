def main():
    names = ['r1', 'r2', 'i1', 'r3', 'i2', 'i3', 'i4']
    s7 = [True if x == '1' else False for x in input("Введите код в формате '0000000': ")]
    d = {key: value for key, value in zip(names, s7)}

    s = [0] * 4
    s[1] = d['r1'] ^ d['i1'] ^ d['i2'] ^ d['i4']
    s[2] = d['r2'] ^ d['i1'] ^ d['i3'] ^ d['i4']
    s[3] = d['r3'] ^ d['i2'] ^ d['i3'] ^ d['i4']

    if all(s[i] == 0 for i in range(len(s))):
        print('Всё хорошо')
    else:
        name = names[s[3] * 4 + s[2] * 2 + s[1] - 1]
        print('Ошибка в бите ' + name)
        d[name] = not d[name]
        print('Правильное сообщение: ' + "".join(['1' if x is True else '0' for x in d.values()]))


if __name__ == '__main__':
    main()