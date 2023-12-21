import pandas as pd
import random
from faker import Faker

# Set seed for reproducibility
random.seed(42)

# Create a Faker instance
faker = Faker()

# Function to generate random book data
def generate_book():
    return {
        'book_name': faker.catch_phrase(),
        'subtitles': faker.sentence(),
        'language': random.choice(['English', 'German', 'French', 'Spanish']),
        'isbn': faker.isbn13(),
        'publish_date': faker.date_this_decade(),
        'book_author': faker.name(),
        'genre_id': random.randint(1, 55),  # Assuming 55 genres
        'price': f'{random.uniform(10, 100):.2f}',
        'book_description': faker.paragraph(),
        'library_id': random.randint(10, 11),  # Assuming 2 libraries
        'doi': faker.uuid4() if random.random() < 0.2 else None  # 20% chance of having DOI
    }

# Generate 10,000 books
books_data = [generate_book() for _ in range(10000)]

# Create a DataFrame
df = pd.DataFrame(books_data)

# Save to Excel file
df.to_excel('books_data.xlsx', index=False, engine='openpyxl')

