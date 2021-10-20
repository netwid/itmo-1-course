import re
from pyfiglet import figlet_format


def first(string):
    return len(re.findall(r'=-\)', string))


def second(string):
    res = re.search(r'ВТ(?:\s*[\w–-]+\s*){,4}ИТМО', string, re.UNICODE)
    if res:
        return res.group()
    return None


def third(string):
    return re.match(r'[\w.]+@(?:\w+\.)+[a-zA-Z]+', string) is not None


def main():
    print(figlet_format('=-)', font='starwars'))
    print('Тесты:')

    print('1:')
    print('\t' + '=-) =-)'.rjust(15) + str(first('=-) =-)')).rjust(10))
    print('\t' + '=-=-)'.rjust(15) + str(first('=-=-)')).rjust(10))
    print('\t' + '=-=)-)'.rjust(15) + str(first('=-=)-)')).rjust(10))
    print('\t' + '=x-x)=x-x)=-)'.rjust(15) + str(first('=x-x)=x-x)=-)')).rjust(10))
    print('\t' + ''.rjust(15) + str(first('')).rjust(10))

    print('2:')
    print('\t' + 'ВТ ВТ ИТМО ИТМО'.rjust(35) + str(second('ВТ ВТ ИТМО ИТМО')).rjust(35))
    print('\t' + 'ВТ ФООЫОЫФО ЫФВОВЫ ЫФО ОФ ЫФ ИТМО'.rjust(35) + str(second('ВТ - кафедра ИТМО')).rjust(35))
    print('\t' + 'ВТ ВТ - кафедра ИТМО ИТМО'.rjust(35) + str(second('ВТ ВТ - кафедра ИТМО ИТМО')).rjust(35))
    print('\t' + 'ВТ ВТ - лучшая кафедра ИТМО ИТМО'.rjust(35) + str(second('ВТ ВТ - лучшая кафедра ИТМО ИТМО')).rjust(35))
    print('\t' + 'ВТ – лучшая кафедра в ИТМО'.rjust(35) + str(second('ВТ – лучшая кафедра в ИТМО')).rjust(35))

    print('3:')
    print('\t' + 'abcdd@gmail.com'.rjust(30) + str(third('abcdd@gmail.com')).rjust(15))
    print('\t' + 'abcdd@very.big.domain.com'.rjust(30) + str(third('abcdd@very.big.domain.com')).rjust(15))
    print('\t' + 'ab-cdd@gmail.com'.rjust(30) + str(third('ab-cdd@gmail.com')).rjust(15))
    print('\t' + 'name.with.dots@gmail.com'.rjust(30) + str(third('name.with.dots@gmail.com')).rjust(15))
    print('\t' + 'dsskd@gmail'.rjust(30) + str(third('dsskd@gmail')).rjust(15))
    print('\t' + 'dsskd@gmail..'.rjust(30) + str(third('dsskd@gmail..')).rjust(15))


if __name__ == '__main__':
    main()
