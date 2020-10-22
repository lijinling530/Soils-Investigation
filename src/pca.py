import numpy as np
from sklearn.decomposition import PCA
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import scale


#Load data set
data = pd.read_csv('work.csv')

#convert it to numpy arrays
X=data.values

#Scaling the values
X = scale(X)

#compute Covariance Matrix
mean_vec = np.mean(X, axis=0)
cov_mat = (X - mean_vec).T.dot((X - mean_vec)) / (X.shape[0]-1)
print('NumPy Covariance Matrix: \n%s' %np.cov(X.T))

#compute eigenvectors and eigenvalues
cov_mat1 = np.cov(X.T)
eig_vals, eig_vecs = np.linalg.eig(cov_mat1)
print('Eigenvectors \n%s' %eig_vecs)
print('\nEigenvalues \n%s' %eig_vals)

# Make a list of (eigenvalue, eigenvector) tuples
eig_pairs = [(np.abs(eig_vals[i]), eig_vecs[:,i]) for i in range(len(eig_vals))]
# Sort the (eigenvalue, eigenvector) tuples from high to low
eig_pairs.sort()
eig_pairs.reverse()
# Visually confirm that the list is correctly sorted by decreasing eigenvalues
print('Eigenvalues in descending order:')
for i in eig_pairs:
    print(i[0])

#Explained Variance
# tot = sum(eig_vals)
# var_exp = [(i / tot)*100 for i in sorted(eig_vals, reverse=True)]
# cum_var_exp = np.cumsum(var_exp)
# trace1 = plt.bar(x=['PC %s' %i for i in range(1,7)], y=var_exp, showlegend=False)
# trace2 = plt.scatter(x=['PC %s' %i for i in range(1,7)],y=cum_var_exp,name='cumulative explained variance')
# data = Data([trace1, trace2])
# layout=Layout(
#         yaxis=YAxis(title='Explained variance in percent'),
#         title='Explained variance by different principal components')
# fig = Figure(data=data, layout=layout)
# py.iplot(fig)


matrix_w = np.hstack((eig_pairs[0][1].reshape(6,1),
                      eig_pairs[1][1].reshape(6,1)))
print('Matrix W:\n%s' %matrix_w)

Y = X.dot(matrix_w)

pca = PCA(n_components=6)

#pca.fit(X)
pca.fit_transform(X)

print(pca)
#X.index.names = ['id']
#X.columns.names = ['heavy metal']
#X.head()

#Y = pca.transform(X)
#Y1=pd.dataframe(Y)
#Y1.index = X.index
#Y1.columns = ['PC1', 'PC2']
#Y1.head()

#The amount of variance that each PC explains
var= pca.explained_variance_ratio_

#Cumulative Variance explains
var1=np.cumsum(np.round(pca.explained_variance_ratio_, decimals=6)*100)

var2=pca.components_
print var1
print var2
plt.plot(var1)
plt.show()

