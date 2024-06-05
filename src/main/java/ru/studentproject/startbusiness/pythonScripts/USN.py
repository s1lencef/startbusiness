# -*- coding: utf-8 -*-
from pypdf import PdfReader, PdfWriter
import psycopg2
import datetime
import os
def completion(all_lines, field_name, field_data):
    for j in range(len(all_lines)):
        field_data["Text"+field_name[j]] = all_lines[j]
    return field_data

def len_splitting(data, data_len, field_name, field_data):
    words = data.split()
    line = ''
    all_lines = []
    j = 0
    if len(words) != 1:
        for i in range(len(words)):
            if len(line + ' ' + words[i]) <= data_len[j]:
                line += words[i]
                if i != len(words) - 1:
                    line += " "
            else:
                all_lines.append(line)
                line = words[i] + ' '
                j += 1
        if i == len(words) - 1:
             all_lines.append(line)
    else:
        all_lines.append(data)
    return completion(all_lines, field_name, field_data)
def change_field_data(DATA, FIELDS_NAME, FIELDS_LEN, field_data):
    for i in range(len(DATA)):
        try:
            if type(DATA[i]).__name__ == 'list':
                field_data = completion(DATA[i], FIELDS_NAME[i], field_data)
            else:
                if len(DATA[i]) >= 1:
                    field_data = len_splitting(DATA[i], FIELDS_LEN[i], FIELDS_NAME[i], field_data)
        except:
            print(DATA[i])
            print(FIELDS_LEN[i])
            print(FIELDS_NAME[i])

    return field_data
def USN(DATA, FIELDS_LEN, FIELDS_NAME, field_data):
    filename = "E:\\LETI\\3_kurs\\5sem\\IT-Projects\\startbusiness\\src\\main\\resources\\documents\\samples\\USN.pdf"
    pdf_reader = PdfReader(filename)
    pdf_writer = PdfWriter()
    pdf_writer.append(pdf_reader)
    field_data = change_field_data(DATA, FIELDS_NAME, FIELDS_LEN, field_data)
    for field, value in field_data.items():
        for page in pdf_writer.pages:
            try:
                pdf_writer.update_page_form_field_values(page, {field: value})
            except:
                pass
    new_file_name = "E:\\LETI\\3_kurs\\5sem\\IT-Projects\\startbusiness\\src\\main\\resources\\documents\\USN_" + DATA[2] + ".pdf"
    with open(new_file_name, 'wb') as output_file:
        pdf_writer.write(output_file)

def start_USN():
    #__Start_DB__
    try:
        conn = psycopg2.connect(dbname='startbusiness', user='business_admin',
                                password='ct0mgB$eYQskZzFg74Qj#QMs', host='79.174.88.55', port='19148')
        cursor = conn.cursor()
    except:
        print("ERROR CONNECT DB")
        return 0

    cursor.execute('SELECT * FROM form WHERE status = 5')
    user_ids = []
    for row in cursor:
        user_ids.append(row)
    cursor.execute('SELECT * FROM employer WHERE form_id =' + str(user_ids[0][0]))
    for row in cursor:
        empl_infs = row


    cursor.execute('SELECT * FROM documents ORDER BY id')
    for row in cursor:
        last_id = row[0]
    insert_query = """ INSERT INTO documents (id, date, file_path, form_id, name, user_id, type)
                                  VALUES (%s, %s, %s, %s, %s, %s, %s)"""
    item_purchase_time = datetime.datetime.now()
    file_path = "E:\\LETI\\3_kurs\\5sem\\IT-Projects\\startbusiness\\src\\main\\resources\\documents\\USN_" + str(empl_infs[6]) + ".pdf"
    item_tuple = (int(last_id) + 1, item_purchase_time, file_path, user_ids[0][0], "USN_" + str(empl_infs[6]) + ".pdf", 24, 2)
    cursor.execute(insert_query, item_tuple)
    print(item_tuple)
    conn.commit()
    cursor.close()
    conn.close()
    # __End_DB__

    ###___DATA___USN___
    FULL_NAME = empl_infs[14] + " " + empl_infs[11] + " " + empl_infs[10] # Format: "ВАСИЛЬЕВА АННА ВИКТОРОВНА"
    INN = str(empl_infs[6])  # Format: "783000229243"
    TAXX = str(int(user_ids[0][5])+1)
    PHONE = str(empl_infs[17]).replace("(", "").replace(")", "").replace("-", "").replace(" ", "") # Format: "+79117615542"
    CUR_DATA = user_ids[0][1].strftime('%d.%m.%Y').split(".")
    ###___DATA___USN___

    DATA_4 = [
        "02.11.2012",
        "MMB-7-3/829@",
        INN,
        "",
        "7814",
        "1",
        FULL_NAME,
        "2",
        "",
        "",
        "",
        TAXX,
        datetime.datetime.now().strftime('%y'),
        "---------",
        "---------",
        "001",
        "1",
        FULL_NAME.split()[0],
        FULL_NAME.split()[1],
        FULL_NAME.split()[2],
        PHONE,
        [CUR_DATA[0], CUR_DATA[1], CUR_DATA[2]]
    ]
    FIELDS_LEN_4 = [
        [12],
        [12],
        [12],
        [9],
        [4],
        [1],
        [40, 40, 40, 40],
        [1],
        [2],
        [2, 2],
        [1],
        [2],
        [9],
        [9],
        [3],
        [20, 20, 20],
        [20],
        [2, 2, 4],
        [1],
        [19, 40],
        [20],
        [20]
    ]
    FIELDS_NAME_4 = [
        ["1"],
        ["2"],
        ["3"],
        ["4"],
        ["5"],
        ["6"],
        ["7.0", "7.1", "7.2", "7.3"],
        ["8"],
        ["9"],
        ["10.0"],
        ["10.1"],
        ["11"],
        ["12"],
        ["13"],
        ["14"],
        ["15"],
        ["16"],
        ["17.0.0"],
        ["17.0.1"],
        ["17.0.2"],
        ["18"],
        ["19.0", "19.1", "20"],

    ]
    USN(DATA_4, FIELDS_LEN_4, FIELDS_NAME_4, {})
start_USN()
